package br.com.onboard.schoolcommand.disciplina.service;

import br.com.onboard.schoolcommand.disciplina.dto.DisciplinaDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class DisciplinaService {
    /*@Autowired
    DisciplinaRepository disciplinaRepository;
*/
/*
    @Autowired
    SchoolQueueSender schoolQueueSender;
*/

    @Transactional
    public DisciplinaDto cadastrar(@Valid DisciplinaDto disciplinaDto) {
       /* Disciplina disciplina = disciplinaDto.converter();'
        disciplinaRepository.save(disciplina);
        schoolQueueSender.send(DisciplinaDto.toGsonString(disciplina), PropertiesClass.getName(disciplina));
        return new DisciplinaDto(disciplina);*/
        return null;

    }
}
