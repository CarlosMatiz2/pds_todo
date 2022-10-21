package co.com.poli.psd_todo.mapper;

public interface IMapper <Input, Output>{
    public Output map(Input in);
}
