package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save(){
        Member member = new Member();
        member.setName("ryu");

        Member result = memberRepository.save(member);

        System.out.println("result : " + (result == member));

//        Assertions.assertEquals(member, null);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findById(){
        Member member = new Member();
        member.setName("ryu");

        Member result = memberRepository.save(member);

        Member member1 = memberRepository.findById(result.getId()).get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    void findByName(){
        Member member = new Member();
        member.setName("sim");

        Member result = memberRepository.save(member);

        Member member1 = memberRepository.findByName(result.getName()).get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll(){
        Member member = new Member();
        member.setName("ryu");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("sim");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
