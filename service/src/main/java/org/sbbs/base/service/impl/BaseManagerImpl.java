package org.sbbs.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.sbbs.base.dao.GeneralDao;
import org.sbbs.base.dao.IBaseDAO;
import org.sbbs.base.dao.hibernate.CallReturn;
import org.sbbs.base.service.IBaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public class BaseManagerImpl<T, ID extends Serializable>
    implements IBaseManager<T, ID> {
    protected final Logger log = LoggerFactory.getLogger( getClass() );

    protected IBaseDAO<T, ID> dao;

    public BaseManagerImpl() {
    }

    public BaseManagerImpl( IBaseDAO<T, ID> genericDao ) {
        this.dao = genericDao;
    }

    @Override
    public T find( ID id ) {

        return dao.find( id );
    }

    @Override
    public T[] find( ID... ids ) {

        return dao.find( ids );
    }

    @Override
    public boolean save( T entity ) {

        return dao.save( entity );
    }

    @Override
    public boolean[] save( T... entities ) {

        return dao.save( entities );
    }

    @Override
    public boolean remove( T entity ) {

        return dao.remove( entity );
    }

    @Override
    public void remove( T... entities ) {

        dao.remove( entities );
    }

    @Override
    public boolean removeById( ID id ) {

        return dao.removeById( id );
    }

    @Override
    public void removeByIds( ID... ids ) {
       
        dao.removeByIds( ids );
    }

    @Override
    public List<T> findAll() {

        return dao.findAll();
    }

    @Override
    public <RT> List<RT> search( ISearch search ) {

        return dao.search( search );
    }

    @Override
    public <RT> RT searchUnique( ISearch search ) {

        return dao.searchUnique( search );
    }

    @Override
    public int count( ISearch search ) {

        return dao.count( search );
    }

    @Override
    public <RT> SearchResult<RT> searchAndCount( ISearch search ) {

        return dao.searchAndCount( search );
    }

    @Override
    public Filter getFilterFromExample( T example ) {

        return dao.getFilterFromExample( example );
    }

    @Override
    public Filter getFilterFromExample( T example, ExampleOptions options ) {

        return dao.getFilterFromExample( example, options );
    }

    @Override
    public boolean exists( ID id ) {

        return dao.exists( id );
    }
    
    GeneralDao generalDao;
    
    
    @Autowired
	public void setGeneralDao(GeneralDao generalDao) {
		this.generalDao = generalDao;
	}

	public CallReturn procedureCall(String procName, Object[] args){
		return this.generalDao.procedureCall(procName, args);
	}
}
