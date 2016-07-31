package com.starter.repositories;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.entity.Reply;
@Repository
public interface ReplyRepository extends GraphRepository<Reply>{

}
