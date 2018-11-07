package kr.or.kosta.levin.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.web.Params;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;

@Bean(type=BeanType.Repository)
public class MybatisAddressDao implements AddressDao {
	
	private static final String NAMESPACE = "kr.or.kosta.levin.user.";
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public boolean create(Address address) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert(NAMESPACE + "createAddress", address);
		sqlSession.commit();
		sqlSession.close();
		return result==1;
	}

	
}










