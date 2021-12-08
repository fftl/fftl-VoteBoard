package fftl.voteboardback.service.other;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class Utils {

    /**
     * 문자열로 이루어진 Id 생성하기(voteId, userId)
     * */
    public String createId(int len){
        Random random = new Random();

        String Id = "";
        for(int i=0; i<len; i++){
            int mixed = random.nextInt(3);
            switch(mixed) {
                //모든 아스키코드 문자를 랜덤으로 돌려도 되나, 혹시혹시나 오류가 생길수도 있으니
                //숫자, 소문자영어, 대문자영어만 포함하도록 하였습니다.
                case 0:
                    Id += (char) ((int) (Math.random() * 10) + 48); //숫자 랜덤!
                    break;
                case 1:
                    Id += (char) ((int) (Math.random() * 25) + 65); //대문자 랜덤!
                    break;
                case 2:
                    Id += (char) ((int) (Math.random() * 25) + 97); //소문자 랜덤!
                    break;
                default:
                    break;
            }
        }
        return Id;
    }

    /**
     * yyyy-MM-dd HH:mm:ss 형식으로 받아들여온 시간을 LocalDateTime 타입으로 변환시켜줍니다.
     * */
    public LocalDateTime timeChange(String s){
        LocalDateTime datetime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return datetime;
    }
}
