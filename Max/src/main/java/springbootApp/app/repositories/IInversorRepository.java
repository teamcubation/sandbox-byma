package  springbootApp.app.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  springbootApp.app.models.Inversor;

@Repository
public interface IInversorRepository extends JpaRepository<Inversor, Long> {

    public Inversor findByDni(String dni);
}