package com.erp.entity;
/**
 * 部门实体类
 * @author Administrator *
 */
public class Dep {	
	private Long uuid;//编号
	private String name;//部门名称
	private String tele;//联系电话

	public Long getUuid() {		
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTele() {		
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Dep{");
		sb.append("uuid=").append(uuid);
		sb.append(", name='").append(name).append('\'');
		sb.append(", tele='").append(tele).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
