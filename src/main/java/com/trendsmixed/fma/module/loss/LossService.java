package com.trendsmixed.fma.module.loss;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LossService {

    private LossRepository lossRepository;

    public List<Loss> findAll() {
        return lossRepository.findAll();
    }

    public Loss save(Loss loss) {
        return lossRepository.save(loss);
    }

    public void save(List<Loss> losses) {
        lossRepository.save(losses);
    }

    public Loss findOne(int id) {
        return lossRepository.findOne(id);
    }

    public void delete(int id) {
        lossRepository.delete(id);
    }

}
