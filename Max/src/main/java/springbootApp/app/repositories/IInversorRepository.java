package springbootApp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  springbootApp.app.models.Inversor;

@Repository
public interface IInversorRepository extends JpaRepository<Inversor, Long> {

    Inversor findByDni(String dni);
}