package common.tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义 Mapper
 * @Author: Dave
 * @Date: 2019/11/8 20:42
 * @Description: TODO
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
