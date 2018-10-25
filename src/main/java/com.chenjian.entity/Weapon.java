package com.chenjian.entity;


import com.chenjian.util.WeaponUtil;

public class Weapon {
	
	String weaponName; //名称
	String weaponDescribe;//品质
	long maxAggressivity;//最大攻击力
	long minAggressivity;//最小攻击力
	
	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getWeaponDescribe() {
		return weaponDescribe;
	}

	public void setWeaponDescribe(String weaponDescribe) {
		this.weaponDescribe = weaponDescribe;
	}

	public long getMaxAggressivity() {
		return maxAggressivity;
	}

	public void setMaxAggressivity(long maxAggressivity) {
		this.maxAggressivity = maxAggressivity;
	}

	public long getMinAggressivity() {
		return minAggressivity;
	}

	public void setMinAggressivity(long minAggressivity) {
		this.minAggressivity = minAggressivity;
	}

	/**
	 * 初始化武器属性
	 */
	public Weapon(){
			
			this.weaponName = WeaponUtil.randomaWeaponName();
			
			this.weaponDescribe = WeaponUtil.randomaWeaponDescribeName(weaponName);
			
			this.minAggressivity = WeaponUtil.getMinAggressivity(weaponDescribe);
			
			this.maxAggressivity = WeaponUtil.getMaxAggressivity(weaponDescribe, minAggressivity);
	}

	
	public void showWeaponInfo(){
		
    	System.out.println("获得武器: "+weaponDescribe + weaponName+"\n");
    	System.out.println("武器属性: "+"\n");
    	System.out.println("攻击力: "+minAggressivity+"-" + maxAggressivity+"\n");
		
	}
}