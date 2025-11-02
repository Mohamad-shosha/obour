package com.platform.obour.service;

import com.platform.obour.dto.*;
import com.platform.obour.entity.Choice;
import com.platform.obour.entity.Question;
import com.platform.obour.entity.Section;
import com.platform.obour.mapper.QuestionMapper;
import com.platform.obour.repository.ChoiceRepository;
import com.platform.obour.repository.QuestionRepository;
import com.platform.obour.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final SectionRepository sectionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionDTO> getBySection(Long sectionId) {
        return questionRepository.findBySectionId(sectionId)
                .stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getById(Long id) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return questionMapper.toDTO(q);
    }

    @Transactional
    public QuestionDTO create(CreateQuestionRequest req) {
        Section section = sectionRepository.findById(req.sectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));

        Question question = questionMapper.toEntity(req, section);
        Question saved = questionRepository.save(question);

        if (req.choices() != null && !req.choices().isEmpty()) {
            List<Choice> choices = questionMapper.toChoiceEntities(req.choices(), saved);
            choiceRepository.saveAll(choices);
            saved.setChoices(choiceRepository.findByQuestionId(saved.getId()));
        }

        return questionMapper.toDTO(saved);
    }

    public List<QuestionDTO> getAll() {
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
