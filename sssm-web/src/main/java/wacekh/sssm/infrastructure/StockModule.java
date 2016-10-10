package wacekh.sssm.infrastructure;

import com.google.inject.AbstractModule;
import wacekh.sssm.repositories.StocksRepository;
import wacekh.sssm.repositories.impl.StocksRepositoryImpl;
import wacekh.sssm.service.StocksService;
import wacekh.sssm.service.impl.StocksServiceImpl;
/**
 * Configure dependency injection for some objects.
 * 
 * @author Waclaw Holub
 *
 */
public class StockModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(StocksService.class).to(StocksServiceImpl.class);
		bind(StocksRepository.class).to(StocksRepositoryImpl.class);
	}
}