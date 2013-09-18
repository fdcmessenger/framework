package org.sbbs.base.service;

import java.io.Serializable;
import java.util.List;

import org.sbbs.base.dao.hibernate.CallReturn;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface IBaseManager<T, ID extends Serializable> {

    /**
     * <p>
     * Get the entity with the specified type and id from the datastore.
     * <p>
     * If none is found, return null.
     */
    public T find( ID id );

    /**
     * Get all entities of the specified type from the datastore that have one of these ids.
     */
    public T[] find( ID... ids );

    /**
     * <p>
     * If the id of the entity is null or zero, add it to the datastore and assign it an id; otherwise, update the
     * corresponding entity in the datastore with the properties of this entity. In either case the entity passed to
     * this method will be attached to the session.
     * <p>
     * If an entity to update is already attached to the session, this method will have no effect. If an entity to
     * update has the same id as another instance already attached to the session, an error will be thrown.
     * 
     * @return <code>true</code> if create; <code>false</code> if update.
     */
    public boolean save( T entity );

    /**
     * <p>
     * For each entity, if the id of the entity is null or zero, add it to the datastore and assign it an id; otherwise,
     * update the corresponding entity in the datastore with the properties of this entity. In either case the entity
     * passed to this method will be attached to the session.
     * <p>
     * If an entity to update is already attached to the session, this method will have no effect. If an entity to
     * update has the same id as another instance already attached to the session, an error will be thrown.
     */
    public boolean[] save( T... entities );

    /**
     * Remove the specified entity from the datastore.
     * 
     * @return <code>true</code> if the entity is found in the datastore and removed, <code>false</code> if it is not
     *         found.
     */
    public boolean remove( T entity );

    /**
     * Remove all of the specified entities from the datastore.
     */
    public void remove( T... entities );

    /**
     * Remove the entity with the specified type and id from the datastore.
     * 
     * @return <code>true</code> if the entity is found in the datastore and removed, <code>false</code> if it is not
     *         found.
     */
    public boolean removeById( ID id );

    /**
     * Remove all the entities of the given type from the datastore that have one of these ids.
     */
    public void removeByIds( ID... ids );

    /**
     * Get a list of all the objects of the specified type.
     */
    public List<T> findAll();

    /**
     * Search for entities given the search parameters in the specified <code>ISearch</code> object.
     * 
     * @param RT The result type is automatically determined by the context in which the method is called.
     */
    public <RT> List<RT> search( ISearch search );

    /**
     * Search for a single entity using the given parameters.
     * 
     * @param RT The result type is automatically determined by the context in which the method is called.
     */
    public <RT> RT searchUnique( ISearch search );

    /**
     * Returns the total number of results that would be returned using the given <code>ISearch</code> if there were no
     * paging or maxResults limits.
     */
    public int count( ISearch search );

    /**
     * Returns a <code>SearchResult</code> object that includes both the list of results like <code>search()</code> and
     * the total length like <code>count()</code>.
     * 
     * @param RT The result type is automatically determined by the context in which the method is called.
     */
    public <RT> SearchResult<RT> searchAndCount( ISearch search );

    /**
     * Generates a search filter from the given example using default options.
     */
    public Filter getFilterFromExample( T example );

    /**
     * Generates a search filter from the given example using the specified options.
     */
    public Filter getFilterFromExample( T example, ExampleOptions options );

    boolean exists( ID id );
    
    
    public CallReturn procedureCall(String procName, Object[] args);

}