package br.com.milebrito.controller.dto.converter;

import br.com.milebrito.beans.Email;
import br.com.milebrito.controller.dto.EmailDTO;

public class EmailDTOConverter {
	

	public static Email fromDTO(EmailDTO dto) {
		
		Email entity = new Email();
		entity.setNomeRemetente(dto.getNome());
		entity.setEnderecoEmailRemetente(dto.getEmail());
		entity.setTextoMensagem(dto.getMensagem());
		
		return entity;
		
	}

}
