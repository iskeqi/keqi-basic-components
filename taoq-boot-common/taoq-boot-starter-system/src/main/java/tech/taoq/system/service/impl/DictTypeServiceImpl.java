package tech.taoq.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.db.DictTypeDO;
import tech.taoq.system.mapper.DictTypeMapper;
import tech.taoq.system.service.DictTypeService;

@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    public void insert(DictTypeDO param) {
        DictTypeDO t = dictTypeMapper.selectOne(Wrappers.query(new DictTypeDO().setType(param.getType())));
        if (t != null) {
            throw new ParamIllegalException("type:" + param.getType() + " 已经存在");
        }

        dictTypeMapper.insert(param);
    }

    public void deleteById(String id) {
        dictTypeMapper.deleteById(id);
    }

    public void updateById(DictTypeDO param) {
        DictTypeDO t1 = BeanUtil.copyProperties(param, DictTypeDO.class);
        // configKey 是不能修改的
        t1.setType(null);
        t1.setCreateTime(null);
        dictTypeMapper.updateById(param);
    }

    public DictTypeDO getById(String id) {
        return dictTypeMapper.selectById(id);
    }

    public PageDto<DictTypeDO> page(Page<DictTypeDO> param) {
        Page<DictTypeDO> page = dictTypeMapper.selectPage(param, Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }
}
