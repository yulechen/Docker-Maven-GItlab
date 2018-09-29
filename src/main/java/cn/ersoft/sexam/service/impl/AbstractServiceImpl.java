package cn.ersoft.sexam.service.impl;


import cn.ersoft.sexam.common.exception.BusinessException;

import cn.ersoft.sexam.constants.ResultCode;
import cn.ersoft.sexam.common.util.BeanCopyUtil;
import cn.ersoft.sexam.service.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static cn.ersoft.sexam.constants.ResultCode.*;

/**
 * @author Wangkun
 * @since 2018/7/21 16:48
 */
public abstract class AbstractServiceImpl<T, ID> implements AbstractService<T, ID> {

    /**
     * 子类设置
     * 
     * @return
     */
    protected abstract JpaRepository<T, ID> getJpaRepository();

    @Override
    public List<T> findAll() {
        return getJpaRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getJpaRepository().findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> iterable) {
        return getJpaRepository().findAllById(iterable);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> iterable) {
        return getJpaRepository().saveAll(iterable);
    }

    @Override
    public void flush() {
        getJpaRepository().flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return getJpaRepository().saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        getJpaRepository().deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        getJpaRepository().deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return getJpaRepository().getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return getJpaRepository().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getJpaRepository().findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getJpaRepository().findAll(pageable);
    }

    @Override
    public <S extends T> S save(S s) {
        return getJpaRepository().save(s);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getJpaRepository().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getJpaRepository().existsById(id);
    }

    @Override
    public long count() {
        return getJpaRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getJpaRepository().deleteById(id);
    }

    @Override
    public void delete(T t) {
        getJpaRepository().delete(t);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        getJpaRepository().deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        getJpaRepository().deleteAll();
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return getJpaRepository().findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getJpaRepository().findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return getJpaRepository().count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return getJpaRepository().exists(example);
    }

    @Override
    public void exception(ResultCode code) {
        BusinessException.throwException(code);
    }

    @Override
    public void exception(String msg) {
       throw  new BusinessException(SERVER_EXCEPTION.getCode(),msg);
    }

    @Override
    public void deleteSoftById(ID id) {
        Optional<T> byId = getJpaRepository().findById(id);
        T one =byId.get();
        Method setIsDeleteMethond = ReflectionUtils.findMethod(one.getClass(), "setIsDelete",Integer.class);
         if(null == setIsDeleteMethond)
             setIsDeleteMethond = ReflectionUtils.findMethod(one.getClass(), "setIsDelete",int.class);
         if(null == setIsDeleteMethond)
             throw new RuntimeException("setIsDelete method is not exist");

         ReflectionUtils.invokeMethod(setIsDeleteMethond,one, 1);
         getJpaRepository().save(one);
    }

    public void updateByObject(T req){
        Method getIdMethod = ReflectionUtils.findMethod(req.getClass(), "getId");
        if(null == getIdMethod){
            throw new RuntimeException("getId method is not exist");
        }
        Long id = (Long) ReflectionUtils.invokeMethod(getIdMethod, req);

        Optional<T> byId = getJpaRepository().findById((ID) id);
        BeanCopyUtil.copyProperties(req,byId.get(),false);
        save(byId.get());
    }
}
