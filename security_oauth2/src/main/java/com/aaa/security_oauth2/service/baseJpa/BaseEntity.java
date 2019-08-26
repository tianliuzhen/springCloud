package com.aaa.security_oauth2.service.baseJpa;

import com.aaa.security_oauth2.util.TimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/23
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {
    public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();
    private static final long serialVersionUID = 5316912770609152144L;

    @JsonIgnore
    @Column(
            name = "is_del",
            length = 1,
            nullable = false,
            columnDefinition = "COMMENT '是否删除'"
    )
    private Integer isDel = 0;

    /**
     *  因为是 jackson 所以 只有在  @ResponseBody 下才会正常显示转换后的格式
     * @param
     * @return
     */
    /*@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;*/
    @CreatedDate
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @CreatedBy
    @ApiModelProperty("创建人")
    private String create_by;
}