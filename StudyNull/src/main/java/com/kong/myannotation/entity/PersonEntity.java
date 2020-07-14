package com.kong.myannotation.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PersonEntity{
	/**
	 * 属性关键字
	 */
    public long id;	//玩家id
    public String name;	//玩家名称
    public int age;	//玩家年龄
    public long leaderId;	//帮主ID
    public String leaderName;	//帮主名字
    public long createId;	//创始人ID
    public String createName;	//创始人名字
    public int tag;	//帮会标签
    public boolean autoJoin;	//自动加入
    public int state;	//运行状态
    public String announcement;	//帮会宣言
    public String bulletin;	//帮会公告
    public long announceTime;	//上一次发布宣言的时间
    public int activeValue;	//帮会活跃值
    public int weekactiveValue;	//本周帮会活跃值
    public int level;	//帮会等级
    public int currPrestige;	//帮会当前声望
    public int totalPrestige;	//帮会总声望
    public int weekPrestige;	//本周帮会声望
    public int currNum;	//当前人数
    public int maxNum;	//最大人数
    public int announcementNum;	//帮会宣言修改次数
    public long createTime;	//创建时间
    public String techJson;	//科技升级信息
    public int isTechAffairsOpened;	//内政今天是否已经开启过了
    public long techAffairsTime;	//内政开启时间
    public long techSpeedUpTime;	//内政加速时间
    public long lastPulishNoticeTime;	//最后一次发布公告的时间
    public int bossLevel;	//帮会BOSS等级
    public int isBossEnd;	//帮会BOSS是否结束
    public long callBossTime;	//上一次召唤BOSS的时间
    public long bossEndTime;	//上一次BOSS结束的时间
    public String bossRewards;	//帮会BOSS奖励信息
    public long combat;	//总战斗力
    public long fund;	//宗门资金
    public int material;	//宗门资材
    public long lowcostTime;	//低维护状态开始时间
    public int requiredLv;	//加入所需等级
    public int requiredFight;	//加入所需战力
    public int freeCount;	//免费群发次数
}