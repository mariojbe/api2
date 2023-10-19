package br.edu.unime.api2.httpClient;

import br.edu.unime.api2.entity.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cep-client", url = "https://brasilapi.com.br/api")
public interface CepHttpClient {

    @GetMapping("/cep/v2/{cep}")
    public Endereco obterEnderecoPorCep(@PathVariable("cep") String cep);

}
