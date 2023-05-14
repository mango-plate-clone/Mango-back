package efub.toy.mangoplate.hearts.service;

import efub.toy.mangoplate.hearts.repository.HeartRepository;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.store.domain.Store;
import efub.toy.mangoplate.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import efub.toy.mangoplate.hearts.domain.Heart;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public String likeStore(Long id, Member member){

        Store store =  storeRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 가게가 없습니다. id=" + id));
        if(heartRepository.findByMemberAndStore(member, store).isPresent()) {
            throw new RuntimeException("이미 찜한 가게입니다.");
        } else {
            heartRepository.save(Heart.builder()
                    .member(member)
                    .store(store)
                    .build());
            return id+"번 가게 찜";
        }
    }

}
