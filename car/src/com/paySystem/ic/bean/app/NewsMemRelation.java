package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



	@Entity
	@Table(name="b_news_mem")
	public class NewsMemRelation implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer Id;//消息id
		private Integer newsId;//消息标题
		private Integer memId;//消息内容
		private Integer ststus;//状态
		private Date createTime;//创建时间
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY )
		@Column
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
		}
		
		@Column(columnDefinition = "char(1)")
		public Integer getNewsId() {
			return newsId;
		}
		public void setNewsId(Integer newsId) {
			this.newsId = newsId;
		}


		@Column(columnDefinition = "char(1)")
		public Integer getMemId() {
			return memId;
		}
		public void setMemId(Integer memId) {
			this.memId = memId;
		}
		
		
		@Column(columnDefinition = "char(1)")
		public Integer getStstus() {
			return ststus;
		}
		public void setStstus(Integer ststus) {
			this.ststus = ststus;
		}
		
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
		
}
