package com.example.movieapp.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEpisodeRequest {
    @NotEmpty(message = "Tên không được để trống")
    String name;

    @NotNull(message = "Thứ tự không được để trống")
    @Min(value = 1, message = "Thứ tự phải lớn hơn hoặc bằng 1")
    Integer displayOrder;

    @NotNull(message = "Trạng thái không được để trống")
    Boolean status;

    @NotNull(message = "ID phim không được để trống")
    Integer movieId;
}
