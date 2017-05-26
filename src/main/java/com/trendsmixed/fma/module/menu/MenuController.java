package com.trendsmixed.fma.module.menu;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.Menu;
import com.trendsmixed.fma.entity.Team;
import com.trendsmixed.fma.entity.TeamMenu;
import com.trendsmixed.fma.entity.User;
import com.trendsmixed.fma.module.menu.MenuView;
import com.trendsmixed.fma.module.appsession.AppSessionService;
import com.trendsmixed.fma.module.teammenu.TeamMenuService;
import com.trendsmixed.fma.module.team.TeamService;
import com.trendsmixed.fma.module.user.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMenuService teamMenuService;

    @JsonView(MenuView.AllAndSubMenu.class)
    @GetMapping
    public List<Menu> findAll() {
        return menuService.findAll();
    }

    @JsonView(MenuView.AllAndSuperMenu.class)
    @GetMapping("/withParent")
    public List<Menu> findAllWithParent() {
        return menuService.findAll();
    }
    @JsonView(MenuView.AllAndSubMenu.class)
    @GetMapping("/top")
    public List<Menu> findTop(@RequestHeader(value = "email", defaultValue = "") String email) {

        AppSession appSession = appSessionService.findOne(email);
        List<Menu> menus = new ArrayList<>();//menuService.findByMenuIsNull();

        if (appSession != null) {
            User user = userService.findByEmail(email);
            if (user == null) {
                appSessionService.delete(email);
                return null;
            }

            Team team = user.getTeam();
            if (team == null) {
                team = teamService.findByName("user");
                if (team == null) {
                    team = new Team();
                    team.setName("user");
                    //List<User> users = new ArrayList<>();
                    //users.add(user);
                    //team.setUserList(users);
                    team = teamService.save(team);
                }
                user.setTeam(team);
                user = userService.save(user);
            } else if (team.getName().equalsIgnoreCase("admin")) {
                List<Menu> allMenus = menuService.findAll();
                List<TeamMenu> teamMenus = new ArrayList<>();
                for (Menu menu : allMenus) {
                    TeamMenu teamMenu = teamMenuService.findByTeamAndMenu(team, menu);
                    if (teamMenu == null) {
                        teamMenu = new TeamMenu();
                        teamMenu.setTeam(team);
                        teamMenu.setMenu(menu);
                        teamMenus.add(teamMenu);
                    }
                }
                teamMenus = teamMenuService.save(teamMenus);
                team.setTeamMenuList(teamMenus);
                team = teamService.save(team);

            }
            if (team.getTeamMenuList() != null) {
                /*
                List<Menu> menusForTeam = teamMenuService.findTopMenuByTeam(team);
                for (Menu menu : menusForTeam) {
                }*/
                menus.addAll(teamMenuService.findTopMenuByTeam(team));
            }
            menus.add(new Menu("Logout", "#logoutModal"));
            menus.add(new Menu("⌕", "#menuGridModal"));
        } else {
            menus.add(new Menu("Login", "#loginModal"));
            menus.add(new Menu("Register", "#registerModal"));
        }

        return menus;
    }

    @PostMapping
    public Menu save(@RequestBody Menu menu, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {

        appSessionService.isValid(email, request);
        try {
            menu = menuService.save(menu);
            return menu;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<Menu> menus, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        AppSession appSession = appSessionService.findOne(email);

        if (appSession != null) {
            appSessionService.isValid(email, request);
            try {
                for (Menu menu : menus) {
                    menu.setName(menu.getName().trim());
                    Menu existingMenu = menuService.findByNameAndMenuIsNull(menu.getName());
                    if (existingMenu != null) {
                        menu.setId(existingMenu.getId());
                    }
                    List<Menu> subMenus = menu.getMenuList();
                    if (subMenus != null) {
                        for (Menu subMenu : subMenus) {
                            Menu existingSubMenu = menuService.findByNameAndMenu(subMenu.getName(), existingMenu);
                            if (existingSubMenu != null) {
                                subMenu.setId(existingSubMenu.getId());
                            }
                            subMenu.setMenu(menu);
                        }
                    }
                }
                menuService.save(menus);
            } catch (Throwable e) {
                e.printStackTrace();
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new Error(e.getMessage());
            }
        }
    }

    @GetMapping("/{id}")
    public Menu findOne(@PathVariable("id") int id) {
        return menuService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        menuService.delete(id);

    }

    @PutMapping("/{id}")
    public Menu updateCustomer(@PathVariable int id, @RequestBody Menu menu, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        menu.setId(id);
        menu = menuService.save(menu);
        return menu;
    }

}