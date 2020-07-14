package com.kong.myannotation.enumm;

import com.kong.myannotation.Conlumn;
import com.kong.myannotation.Kong;


@Kong(tableName = "Person")
public enum Person {
    @Conlumn(type = long.class, comment = "玩家id", index = true)
    id,
    @Conlumn(type = String.class, comment = "玩家名称")
    name,
    @Conlumn(type = int.class, comment = "玩家年龄", defaults = "0")
    age,
    @Conlumn(type=long.class, comment="帮主ID")
    leaderId,
    @Conlumn(type=String.class, comment="帮主名字")
    leaderName,
    @Conlumn(type=long.class, comment="创始人ID")
    createId,
    @Conlumn(type=String.class, comment="创始人名字")
    createName,
    @Conlumn(type=int.class, comment="帮会标签")
    tag,
    @Conlumn(type=boolean.class, comment="自动加入")
    autoJoin,
    @Conlumn(type=int.class, comment="运行状态")
    state,
    @Conlumn(type=String.class, comment="帮会宣言")
    announcement,
    @Conlumn(type=String.class, comment="帮会公告")
    bulletin,
    @Conlumn(type=long.class, comment="上一次发布宣言的时间")
    announceTime,
    @Conlumn(type=int.class, comment="帮会活跃值", defaults = "0")
    activeValue,
    @Conlumn(type=int.class, comment="本周帮会活跃值", defaults = "0")
    weekactiveValue,
    @Conlumn(type=int.class, comment="帮会等级", defaults = "0")
    level,
    @Conlumn(type=int.class, comment="帮会当前声望", defaults = "0")
    currPrestige,
    @Conlumn(type=int.class, comment="帮会总声望")
    totalPrestige,
    @Conlumn(type=int.class, comment="本周帮会声望", defaults = "0")
    weekPrestige,
    @Conlumn(type=int.class, comment="当前人数", defaults = "0")
    currNum,
    @Conlumn(type=int.class, comment="最大人数")
    maxNum,
    @Conlumn(type=int.class, comment="帮会宣言修改次数", defaults = "0")
    announcementNum,
    @Conlumn(type=long.class, comment="创建时间")
    createTime,
    @Conlumn(type=String.class, comment="科技升级信息",defaults = "[]")
    techJson,
    @Conlumn(type=int.class, comment="内政今天是否已经开启过了", defaults = "0")
    isTechAffairsOpened,
    @Conlumn(type=long.class, comment="内政开启时间", defaults = "0")
    techAffairsTime,
    @Conlumn(type=long.class, comment="内政加速时间", defaults = "0")
    techSpeedUpTime,
    @Conlumn(type=long.class, comment="最后一次发布公告的时间", defaults = "0")
    lastPulishNoticeTime,
    @Conlumn(type=int.class, comment="帮会BOSS等级", defaults="1")
    bossLevel,
    @Conlumn(type=int.class, comment="帮会BOSS是否结束")
    isBossEnd,
    @Conlumn(type=long.class, comment="上一次召唤BOSS的时间", defaults = "0")
    callBossTime,
    @Conlumn(type=long.class, comment="上一次BOSS结束的时间", defaults = "0")
    bossEndTime,
    @Conlumn(type=String.class, comment="帮会BOSS奖励信息",defaults = "[]")
    bossRewards,
    @Conlumn(type=long.class, comment="总战斗力")
    combat,
    @Conlumn(type=long.class, comment="宗门资金")
    fund,
    @Conlumn(type=int.class, comment="宗门资材")
    material,
    @Conlumn(type=long.class, comment="低维护状态开始时间", defaults = "0")
    lowcostTime,
    @Conlumn(type=int.class, comment="加入所需等级")
    requiredLv,
    @Conlumn(type=int.class, comment="加入所需战力")
    requiredFight,
    @Conlumn(type=int.class, comment="免费群发次数")
    freeCount,
}


