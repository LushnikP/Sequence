package com.example.test.websocket;

import com.example.test.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/seq")
@RequiredArgsConstructor
public class SequenceController {
    private final SequenceService sequenceService;

    @GetMapping()
    public List<Sequence> getSequence(){
        return sequenceService.getSequence();
    }

    @GetMapping("generate")
    public List<Sequence> getGenerateSequence(){
        return sequenceService.generateBtn();
    }

    @PostMapping
    public List<Sequence> addSequence(@RequestBody Integer length){
        Sequence sequence = new Sequence(length);
        sequenceService.addSequence(sequence);
        return sequenceService.getSequence();
    }
}
