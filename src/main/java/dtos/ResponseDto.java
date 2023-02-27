package dtos;

public abstract class ResponseDto {
    private ResponseStatusDto responseStatusDto;

    public ResponseStatusDto getResponseStatusDto() {
        return responseStatusDto;
    }

    public void setResponseStatusDto(ResponseStatusDto responseStatusDto) {
        this.responseStatusDto = responseStatusDto;
    }
}
