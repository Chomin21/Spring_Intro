package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //키값을 생성해줌.(0, 1, 2,,,)
    @Override
    public Member save(Member member) {
        member.setId(++sequence);//키를 증가시켜서 멤버의 id로 할당.
        store.put(member.getId(), member); //store에 저장.
        return member; //저장한 멤버 반환.
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null로 반환될 가능성이 있으면 Optional로 감쌈.
            //Optional.ofNullable : null이어도 반환됨.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //파라미터로 넘어온 name과 같은지 확인한 뒤, 같은 것을 필터링해서 반환.
        //findAny() : 1개 찾으면 바로 반환. 끝까지 돌았는데 없으면 Optional에 null이 포함돼서 반환.
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){ //store를 비워주는 함수.
        store.clear();
    }



}
