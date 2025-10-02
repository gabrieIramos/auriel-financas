package com.auriel.auriel_financas.service;

import org.springframework.stereotype.Service;
import com.auriel.auriel_financas.model.Carteira;
import com.auriel.auriel_financas.repository.CarteiraRepository;
import com.auriel.auriel_financas.model.Usuario;
import com.auriel.auriel_financas.repository.UsuarioRepository;
import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraCreateDTO;
import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraResponseDTO;
import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraUpdateDTO;
import com.auriel.auriel_financas.exception.GlobalException;

import java.util.List;

@Service
public class CarteiraService {

        private final CarteiraRepository carteiraRepository;
        private final UsuarioRepository usuarioRepository;

        public CarteiraService(CarteiraRepository carteiraRepository, UsuarioRepository usuarioRepository) {
                this.carteiraRepository = carteiraRepository;
                this.usuarioRepository = usuarioRepository;
        }

        public CarteiraResponseDTO criarCarteira(CarteiraCreateDTO carteiraCreateDTO) {
                Usuario usuario = usuarioRepository.findById(carteiraCreateDTO.getId_usuario())
                                .orElseThrow(GlobalException::usuarioNaoEncontrado);

                Carteira carteira = new Carteira();
                carteira.setNome(carteiraCreateDTO.getNome());
                carteira.setDescricao(carteiraCreateDTO.getDescricao());
                carteira.setUsuario(usuario);

                Carteira carteiraSalva = carteiraRepository.save(carteira);

                return new CarteiraResponseDTO(
                                carteiraSalva.getIdCarteira(),
                                carteiraSalva.getUsuario().getIdUsuario(),
                                carteiraSalva.getNome(),
                                carteiraSalva.getDescricao(),
                                carteiraSalva.getDataCriacao());
        }

        public List<CarteiraResponseDTO> listarCarteirasPorUsuario(Long idUsuario) {

                if (!usuarioRepository.existsById(idUsuario)) {
                        throw GlobalException.usuarioNaoEncontrado();
                }

                List<Carteira> carteiras = carteiraRepository.findByUsuarioIdUsuario(idUsuario);
                return carteiras.stream()
                                .map(carteira -> new CarteiraResponseDTO(
                                                carteira.getIdCarteira(),
                                                carteira.getUsuario().getIdUsuario(),
                                                carteira.getNome(),
                                                carteira.getDescricao(),
                                                carteira.getDataCriacao()))
                                .toList();
        }

        public CarteiraResponseDTO atualizarCarteira(Long idCarteira, CarteiraUpdateDTO carteiraUpdateDTO) {
                Carteira carteira = carteiraRepository.findById(idCarteira)
                                .orElseThrow(GlobalException::carteiraNaoEncontrada);

                if (carteiraUpdateDTO.getNome() != null) {
                        carteira.setNome(carteiraUpdateDTO.getNome());
                }
                if (carteiraUpdateDTO.getDescricao() != null) {
                        carteira.setDescricao(carteiraUpdateDTO.getDescricao());
                }

                Carteira carteiraAtualizada = carteiraRepository.save(carteira);

                return new CarteiraResponseDTO(
                                carteiraAtualizada.getIdCarteira(),
                                carteiraAtualizada.getUsuario().getIdUsuario(),
                                carteiraAtualizada.getNome(),
                                carteiraAtualizada.getDescricao(),
                                carteiraAtualizada.getDataCriacao());
        }


}
