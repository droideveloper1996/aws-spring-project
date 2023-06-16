package com.example.aws.awsstarter;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends JpaRepository<Book,Integer> {


}
