package com.stslex.meal.domain.mapper

import com.stslex.core.Mapper
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.ui.model.ImageModel

class PagingImageMapper : Mapper.Data<ImageEntity, ImageModel> {

    override fun map(data: ImageEntity): ImageModel = with(data) {
        ImageModel.Base(
            id = id,
            url = urls.regular,
            description = description ?: "Title"
        )
    }
}