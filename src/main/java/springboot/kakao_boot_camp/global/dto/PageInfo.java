package springboot.kakao_boot_camp.global.dto;

import org.springframework.data.domain.Page;

public record PageInfo(
        int currentPage,
        int totalPages,
        long totalElements,
        boolean hasNext
) {
    public static PageInfo of(Page<?> page) {
        return new PageInfo(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }
}
