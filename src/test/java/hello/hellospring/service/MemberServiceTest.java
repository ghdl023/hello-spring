package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    private MemberService memberService = new MemberService();
//    private MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // (2)

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clear();
    }

    @Test
    void 회원가입() {
        //given
        Member m = new Member();
        m.setName("ryu");

        //when
        Long saveId = memberService.join(m);
        
        //then
        Member findMember = memberService.findOne(saveId).get(); // (1) memberService의 메소드를 이용하면 같은 memberRepository를 참조한다.
//        Member findMember = memberRepository.findById(saveId).get(); // (2) 그러나 new 로 생성한 memberRepository는 meberService에 있는 repository랑은 다른 객체이게 됨.

        Assertions.assertThat(m.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 회원가입_중복_예외(){
        //given
        Member m = new Member();
        m.setName("ryu");

        Member m2 = new Member();
        m2.setName("ryu");

        //when
        memberService.join(m);
//        try {
//            memberService.join(m);
//            fail(); // ? 왜잇는거지
//        } catch(IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.222");
//        }

//        assertThrows(NullPointerException.class, () -> memberService.join(m2)); // IllegalStateException이 발생해야하는데 NullPointerException 발생해서 fail!

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2)); // 예외가 발생해야 한다.

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}