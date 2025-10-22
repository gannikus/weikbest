package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdServiceCommitment;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品服务承诺表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdServiceCommitmentMapStruct extends BaseMapStruct {
    ProdServiceCommitmentMapStruct INSTANCE = Mappers.getMapper(ProdServiceCommitmentMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodServiceCommitment entity
     * @return dto
     */
    ProdServiceCommitmentDTO converToDTO(ProdServiceCommitment prodServiceCommitment);

    /**
     * DTO转换为entity
     *
     * @param prodServiceCommitmentDTO dto
     * @return entity
     */
    ProdServiceCommitment converToEntity(ProdServiceCommitmentDTO prodServiceCommitmentDTO);

}
