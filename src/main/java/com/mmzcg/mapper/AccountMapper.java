package com.mmzcg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mmzcg.entity.Account;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends BaseMapper<Account> {


    IPage<GameListOuput> selectGameList(@Param("gameListOuputPage") Page<GameListOuput> gameListOuputPage, @Param("searchParams") GameListInput searchParams);
}
