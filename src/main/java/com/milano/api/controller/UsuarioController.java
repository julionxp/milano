package com.milano.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.milano.api.model.Endereco;
import com.milano.api.model.Usuario;
import com.milano.api.repository.ProdutoRepository;
import com.milano.api.repository.UsuarioRepository;

@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProdutoRepository produtoRepository;





    @GetMapping("/home")
    public String exibirHome(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("brasileiras", produtoRepository.findByCategoria("Brasileiras"));
        model.addAttribute("internacionais", produtoRepository.findByCategoria("Internacionais"));
        model.addAttribute("selecoes", produtoRepository.findByCategoria("Seleções"));
        return "/home";
    }


    


    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/cadastro";
    }

    @PostMapping("/cadastro")
    public String register(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }





    @GetMapping("/perfil")
    public String mostrarPerfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
    
    @PostMapping("/perfil/atualizar")
    public String atualizarPerfil(@ModelAttribute Usuario usuarioAtualizado, @AuthenticationPrincipal UserDetails userDetails) {
    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        usuario.setNome(usuarioAtualizado.getNome());



        if (usuario.getEndereco() == null) {
        usuario.setEndereco(new Endereco());
        usuario.getEndereco().setUsuario(usuario);
        }

        usuario.getEndereco().setCep(usuarioAtualizado.getEndereco().getCep());
        usuario.getEndereco().setRua(usuarioAtualizado.getEndereco().getRua());
        usuario.getEndereco().setNumero(usuarioAtualizado.getEndereco().getNumero());
        usuario.getEndereco().setApartamento(usuarioAtualizado.getEndereco().getApartamento());
        usuario.getEndereco().setCidade(usuarioAtualizado.getEndereco().getCidade());
        usuario.getEndereco().setEstado(usuarioAtualizado.getEndereco().getEstado());
       


        usuarioRepository.save(usuario);
        return "redirect:/perfil?atualizado=true";
    
    }





    @GetMapping("/checkout")
    public String exibirCheckot(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/checkout";
    }


}
