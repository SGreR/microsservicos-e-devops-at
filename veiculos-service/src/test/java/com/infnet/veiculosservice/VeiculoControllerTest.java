package com.infnet.veiculosservice;

import com.infnet.veiculosservice.controller.VeiculoController;
import com.infnet.veiculosservice.model.Veiculo;
import com.infnet.veiculosservice.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    private Veiculo veiculo;
    private Veiculo veiculo2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setModelo("Modelo Teste");
        veiculo.setMarca("Marca Teste");
        veiculo.setAno(2020);

        veiculo2 = new Veiculo();
        veiculo.setId(2L);
        veiculo.setModelo("Modelo Teste 2");
        veiculo.setMarca("Marca Teste 2");
        veiculo.setAno(2021);
    }

    @Test
    void listVeiculos_shouldReturnListOfVeiculos_whenVeiculosExist() {
        List<Veiculo> veiculoList = listOf(veiculo, veiculo2);
        when(veiculoService.findAll()).thenReturn(veiculoList);

        ResponseEntity<List<Veiculo>> response = veiculoController.listVeiculos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoList, response.getBody());
        verify(veiculoService, times(1)).findAll();
    }

    @Test
    void listVeiculos_shouldReturnNoContent_whenNoVeiculosExist() {
        when(veiculoService.findAll()).thenReturn(List.of());

        ResponseEntity<List<Veiculo>> response = veiculoController.listVeiculos();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(veiculoService, times(1)).findAll();
    }

    @Test
    void findVeiculoById_shouldReturnVeiculo_whenVeiculoExists() {
        when(veiculoService.findById(anyLong())).thenReturn(Optional.of(veiculo));

        ResponseEntity<Veiculo> response = veiculoController.findVeiculoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).findById(1L);
    }

    @Test
    void findVeiculoById_shouldReturnNotFound_whenVeiculoDoesNotExist() {
        when(veiculoService.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Veiculo> response = veiculoController.findVeiculoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(veiculoService, times(1)).findById(1L);
    }

    @Test
    void postVeiculo_shouldCreateVeiculoAndReturnCreatedStatus() {
        when(veiculoService.save(any(Veiculo.class))).thenReturn(veiculo);

        ResponseEntity<?> response = veiculoController.postVeiculo(veiculo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).save(veiculo);
    }

    @Test
    void putVeiculo_shouldUpdateVeiculo_whenVeiculoExists() {
        when(veiculoService.findById(anyLong())).thenReturn(Optional.of(veiculo));
        when(veiculoService.save(any(Veiculo.class))).thenReturn(veiculo);

        ResponseEntity<?> response = veiculoController.putVeiculo(1L, veiculo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).findById(1L);
        verify(veiculoService, times(1)).save(veiculo);
    }

    @Test
    void putVeiculo_shouldReturnNotFound_whenVeiculoDoesNotExist() {
        when(veiculoService.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = veiculoController.putVeiculo(1L, veiculo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(veiculoService, times(1)).findById(1L);
        verify(veiculoService, times(0)).save(any(Veiculo.class));
    }

    @Test
    void deleteVeiculo_shouldDeleteVeiculoAndReturnNoContent() {
        ResponseEntity<?> response = veiculoController.deleteVeiculo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(veiculoService, times(1)).delete(1L);
    }
}

