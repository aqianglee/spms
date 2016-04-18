package com.aqiang.spms.dao;

import java.util.Date;

public interface Auditable {
	public void setCreateTime(Date date);

	public Date getCreateTime();

	public void setUpdateTime(Date date);

	public Date getUpdateTime();

	public void setCreateUid(String uid);

	public String getCreateUid();

	public void setUpdateUid(String uid);

	public String getUpdateUid();
}
