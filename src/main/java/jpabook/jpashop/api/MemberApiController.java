package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.servicce.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;


    @GetMapping("/api/v1/members")
    public Result memberList() {
        List<Member> findmembers = memberService.findMembers();

        List<memberListDto> collect =
                findmembers.stream().map(m -> new memberListDto(m.getName()))
                        .collect(Collectors.toList());
        return new Result(collect.size() ,collect);


    }
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PutMapping("/api/members/{id}")
    public updateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {

         memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        updateMemberResponse response = new updateMemberResponse(id, findMember.getName());

        return response;
    }

    @Data
    @AllArgsConstructor
    private static class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    private static class memberListDto {
        
        /*조회 api 도 entity 정보 전체가 아닌 보여줄 속성만 따로 dto 로 정하기 
        * entity 자체를 노출하면 안됨*/
        private String name;

    }
    @Data
    @AllArgsConstructor
    private static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    private static class updateMemberResponse {
        private Long id;
        private String name;
    }








    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }


}
