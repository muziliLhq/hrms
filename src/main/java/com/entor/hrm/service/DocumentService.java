package com.entor.hrm.service;

import com.entor.hrm.po.Document;
import com.entor.hrm.service.impl.PageModel;

import java.util.Date;
import java.util.List;

public interface DocumentService {

    /**
     * 根据id查询文档
     * @param id
     * @return
     */
    Document getById(Integer id);

    /**
     * 分页查询文档
     * @param document
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageModel<Document> getByPage(Document document, Integer pageIndex, Integer pageSize);

    /**
     * 根据id移除文档
     * @param id
     */
    void removeDocument(Integer id);

    /**
     * 添加文档
     * @param document
     */
    void save(Document document);

    /**
     * 修改文档
     * @param document
     */
    void modifyDocument(Document document);

    /**
     * 根据id批量删除文档
     * @param ids
     */
    void batchRemoveDocument(Integer[] ids);
}
