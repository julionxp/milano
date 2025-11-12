package com.milano.api.controller;

import com.milano.api.model.CarrinhoItem;
// import com.milano.api.model.ItemCarrinho;
import com.milano.api.model.Produto;
import com.milano.api.repository.ProdutoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/adicionar")
    public String adicionarAoCarrinho(@RequestParam Long produtoId, HttpSession session) {
        // Busca o produto no banco
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        // Pega o carrinho da sessão (ou cria novo)
        List<CarrinhoItem> carrinho = (List<CarrinhoItem>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }

        // Verifica se o produto já está no carrinho
        boolean jaExiste = false;
        for (CarrinhoItem item : carrinho) {
            if (item.getProduto().getId().equals(produtoId)) {
                item.setQuantidade(item.getQuantidade() + 1);
                jaExiste = true;
                break;
            }
        }

        if (!jaExiste) {
            carrinho.add(new CarrinhoItem(produto, 1));
        }

        // Salva o carrinho na sessão
        session.setAttribute("carrinho", carrinho);

        return "redirect:/carrinho";
    }

    @GetMapping
    public String exibirCarrinho(HttpSession session, Model model) {
        List<CarrinhoItem> carrinho = (List<CarrinhoItem>) session.getAttribute("carrinho");
        if (carrinho == null) carrinho = new ArrayList<>();

        double total = carrinho.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();

        model.addAttribute("carrinho", carrinho);
        model.addAttribute("total", total);
        return "carrinho";
    }





}
