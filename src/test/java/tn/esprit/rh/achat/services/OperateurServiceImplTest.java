package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OperateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OperateurServiceImplTest {
    @MockBean
    private OperateurRepository operateurRepository;

    @Autowired
    private OperateurServiceImpl operateurServiceImpl;


    @Test
    void testRetrieveAllOperateurs() {
        List<Operateur> actualRetrieveAllOperateursResult = this.operateurServiceImpl.retrieveAllOperateurs();
        Assertions.assertEquals(0, actualRetrieveAllOperateursResult.size());
    }


    @Test
    void testAddOperateur() {
        Operateur operateur = new Operateur();
        operateur.setFactures(new HashSet<>());
        operateur.setIdOperateur(1L);
        operateur.setNom("Nom");
        operateur.setPassword("azerty");
        operateur.setPrenom("Prenom");
        when(operateurRepository.save(any())).thenReturn(operateur);

        Operateur operateur1 = new Operateur();
        operateur1.setFactures(new HashSet<>());
        operateur1.setIdOperateur(1L);
        operateur1.setNom("Nom");
        operateur1.setPassword("1234");
        operateur1.setPrenom("Prenom");
        assertSame(operateur1, operateurServiceImpl.addOperateur(operateur1));
        verify(operateurRepository).save(any());
    }


    @Test
    void testDeleteOperateur() {
        doNothing().when(operateurRepository).deleteById((Long) any());
        operateurServiceImpl.deleteOperateur(123L);
        verify(operateurRepository).deleteById((Long) any());
    }


    @Test
    void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setFactures(new HashSet<>());
        operateur.setIdOperateur(1L);
        operateur.setNom("Nom");
        operateur.setPassword("1234");
        operateur.setPrenom("Prenom");
        when(operateurRepository.save((Operateur) any())).thenReturn(operateur);

        Operateur operateur1 = new Operateur();
        operateur1.setFactures(new HashSet<>());
        operateur1.setIdOperateur(1L);
        operateur1.setNom("Nom");
        operateur1.setPassword("12345");
        operateur1.setPrenom("Prenom");
        assertSame(operateur1, operateurServiceImpl.updateOperateur(operateur1));
        verify(operateurRepository).save((Operateur) any());
    }


    @Test
    void testRetrieveOperateur() {
        Operateur operateur = new Operateur();
        operateur.setFactures(new HashSet<>());
        operateur.setIdOperateur(1L);
        operateur.setNom("Nom");
        operateur.setPassword("azerty");
        operateur.setPrenom("Prenom");
        Optional<Operateur> ofResult = Optional.of(operateur);
        when(operateurRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(operateur, operateurServiceImpl.retrieveOperateur(123L));
        verify(operateurRepository).findById((Long) any());
    }
}



