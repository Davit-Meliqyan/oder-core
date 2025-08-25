package com.odercore.administration.member.controller;

import com.odercore.administration.member.dto.request.patch.MemberPatchDto;
import com.odercore.administration.member.dto.request.upsert.MemberUpsertDto;
import com.odercore.administration.member.dto.response.MemberDto;
import com.odercore.administration.member.entity.Member;
import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Members", description = "Members Management")
public class MemberController extends AbstractCrudController<
        Member,
        MemberDto,
        MemberUpsertDto,
        MemberPatchDto
        > {

    protected MemberController(
            AbstractCrudService<
                    Member,
                    MemberDto,
                    MemberUpsertDto,
                    MemberPatchDto> service) {
        super(service);
    }
}
