package umc7.spring.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom
{
}