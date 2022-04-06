package com.example.test.websocket;

import com.example.test.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SequenceService {

    private final List<Sequence> sequenceList = new ArrayList<>();

    public List<Sequence> getSequence() {
        return sequenceList;
    }

    public void addSequence(Sequence ...sequence) {
        if(sequenceList.size() < 5){
            for(int i = 0; i < sequence.length; i++){
                sequenceList.add(sequence[i]);
            }
        }else {
            sequenceList.clear();
            sequenceList.add(sequence[0]);
        }
    }

    public List<Sequence> generateBtn(){
        List<Sequence> newGeneratedList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();
        if(!sequenceList.isEmpty()){
            for (int i = 0; i < 5; i++){
                numberList.clear();
                int[] arr = sequenceList.get(i).getGeneratedSeq();
                int rand;
                for(int j = 0; j < 6; j++){
                    rand = (int) (Math.random() * arr.length);
                    if(!numberList.contains(arr[rand])) {
                        numberList.add(arr[rand]);
                    } else j--;
                }
                newGeneratedList.add(new Sequence(numberList));
            }
        }
        return newGeneratedList;
    }
}
