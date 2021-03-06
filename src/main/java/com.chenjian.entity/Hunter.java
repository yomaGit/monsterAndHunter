package com.chenjian.entity;


import com.chenjian.enums.MonsterGradeEnums;
import com.chenjian.util.GameUtil;

public class Hunter {   //爱丽丝
    String name;
    long maxLife;
    long curLife;
    boolean isLive;
    Weapon weapon;
    long maxAttack;         //最大攻击力
    long minAttack;			//最小攻击力
    long defend;         //防御力
    long level;
    long exp;
    long needExp;
    long agile;
    long hideRate;

    public Hunter(String name){
        this.name = name;
        this.maxLife = 100;
        this.curLife = maxLife;
        isLive = true;
        defend = 3;
        level = 1;
        exp = 0;
        needExp = level * 100;
        agile = 35;
        hideRate = 25;
        
        this.maxAttack = 5;
        this.minAttack = 1;
    }
    
    /**
     * 获取武器
     */
    public void getWeapon(){
    	
    	Weapon weapon = new Weapon();
    	
    	this.maxAttack += weapon.maxAggressivity;
    	this.minAttack += weapon.minAggressivity;
    	this.weapon = weapon;
    	
    	if(weapon.weaponName.equals("未知")){
    		
    		System.out.println("悲剧, 没有随机到武器 ......."+"\n");
    		
    		return ;
    	}
    	
    	weapon.showWeaponInfo();
    }
    
    /**
     * 战斗
     * @param monster
     */
    public void fight(Monster monster){    
        
    	if(monster.isLive){
	        
        	if(isLive){
        		
        		if(weapon.weaponName.equals("未知")){
        			
        			 System.out.println("没有武器, "+"【"+name+"】"+"一脸无奈的举起双拳"+", 死盯着"+monster.type+"的动向"+"\r\n");
        		}else{
    		        System.out.println("【"+name+"】"+"无情的拿起"+weapon.getWeaponDescribe()+weapon.getWeaponName()+"杀向"+monster.type+"\r\n");
        		}
        		
        		 monster.injured(this);
	        
	        }else{
	            System.out.println("主角"+"【"+name+"】"+"已经牺牲了"+"\r\n");
	        }
        }else{
            System.out.println("拜托啊！这个丧尸已经被你打死啦！"+"\r\n");
        }
    }

    /**
     * 扣血
     * @param monster
     */
    public void injured(Monster monster){ 
        //增加躲避的判断
        if(monster.grade == MonsterGradeEnums.SHI_XUE.getShowName()){
            if(GameUtil.hidden(this.hideRate)){
                System.out.println("【"+name+"】"+" 机智的躲过了攻击 "+"\r\n");
                showHunterInfo();
                fight(monster);
                return;
            }
          
            System.out.println("【"+name+"】"+":啊啊啊, 巨疼.....嗯? 这怪物还吸血?!!!"+"\r\n");
            
            long lostLife = GameUtil.calLostLife(monster.maxAttack, monster.minAttack, this.defend);
            
            System.out.println("【"+name+"】"+" 血量: -"+lostLife+"\r\n");
            
            curLife-=lostLife;
            if(curLife < 1){
                curLife=0;
                died();
                return;
            }
            
            monster.curLife = (int) (monster.curLife + this.curLife/10);
            
            System.out.println(monster.type+":血量增加 "+(this.curLife/10)+"\r\n");
            
            showHunterInfo();
            fight(monster);
        }else{
	        if(GameUtil.hidden(monster.hideRate)){
	            System.out.println("【"+name+"】"+" 机智的躲过了攻击"+"\r\n");
	            showHunterInfo();
	            fight(monster);
	            return;
	        }
	       
	        System.out.println("【"+name+"】"+":疼疼疼疼疼疼疼疼......"+"\r\n");
	        
	        long lostLife = GameUtil.calLostLife(monster.maxAttack, monster.minAttack, this.defend);
	       
	        System.out.println("【"+name+"】"+" 血量: -"+lostLife+"\r\n");
	        
	        curLife-=lostLife;
	       
	        if(curLife < 1){
	            curLife=0;
	            died();
	            return;
	        }
	        showHunterInfo();
	        fight(monster);
        }
    }
    
    /**
     * 增加经验
     * @param monster
     */
    public void expAdd(Monster monster){
        this.exp+=monster.maxLife;
        this.needExp = 0;
        for(int i=1;i<=level;i++){
            needExp+=i*100;
        }
        if(exp>=needExp){
            upgrade();
        }
    }
    
    /**
     * 升级
     */
    public void upgrade(){
        maxAttack += 8;
        minAttack += 5;
        level ++ ;
        defend += 3;
        maxLife += 10;
        curLife = maxLife;
        
        System.out.println("-----------------------等级提升----------------------------"+"\r\n");
        System.out.println("系统提示：升级啦!"+"\r\n");
        showHunterInfo();
    }
    
    /**
     * 死亡
     */
    public void died(){
        System.out.println("【"+name+"】"+" 被怪物咬死了 , 头都不见了, 惨不忍睹..."+"\r\n");
        isLive = false;
        showHunterInfo();
    }
  
    /**
     * 展示信息
     */
    public void showHunterInfo(){
    	
    	//计算升级需要的经验
    	if(exp >= needExp){
    		
            for(int i=1;i<=level;i++){
                needExp+=i*100;
            }
    	}
    
        System.out.println("【"+name+"】"+" , 等级"+level+" ,血量"+curLife+" ,攻击力"+minAttack+"-"+maxAttack+" ,防御力"+defend+" ,当前经验: "+exp+"/"+needExp+"\r\n");
    }

}