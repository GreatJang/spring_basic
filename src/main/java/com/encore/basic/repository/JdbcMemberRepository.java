package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // 싱글톤 등록 해주어야 Autowired
public class JdbcMemberRepository implements MemberRepository {
    //    Datasource는 DB와 JDBC에서 사용하는 DB연결 드라이버 객체
    @Autowired
    private DataSource dataSource;

    @Override
    public Member save(Member member) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "insert into member(name, email, password) values(?, ?, ?)"; // DB에 데이터 insert
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getName()); // ?값 세팅
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from member"; // DB 데이터 select/
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime now = resultSet.getTimestamp("create_time").toLocalDateTime();
                Member member = new Member(name, email, password);
                member.setId(id);
                member.setCreate_time(now);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Optional<Member> findById(int id) {
        Member member = null;
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from member where id = ?"; // DB 데이터 select/
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id); // ?값 세팅

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            LocalDateTime now = resultSet.getTimestamp("create_time").toLocalDateTime();
            member = new Member(name, email, password);
            member.setId(id);
            member.setCreate_time(now);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(member);
    }
}
