package org.jboss.spring3_2.example.AsyncRequestMapping.repo;

import java.util.List;

import org.jboss.spring3_2.example.AsyncRequestMapping.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
