package br.com.chess.dto;

public class BuscaDto {

    private int page;
    private int pageSize;

    public BuscaDto() {
        // Construtor padrçao
    }

    public BuscaDto(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
