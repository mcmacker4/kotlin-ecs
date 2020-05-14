import com.mcmacker4.ecs.Component
import com.mcmacker4.ecs.Entity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Component1 : Component()

class Component2 : Component()

class ECSTest {
    
    private lateinit var entity: Entity
    
    @BeforeEach
    fun pretest() {
        entity = Entity()
    }
    
    @Test
    fun addComponent() {
        assertFalse(entity.hasComponent(Component1::class))
        assertFalse(entity.hasComponent(Component2::class))
        
        entity.addComponent(Component1())
        
        assertTrue(entity.hasComponent(Component1::class))
        assertFalse(entity.hasComponent(Component2::class))
    }
    
    @Test
    fun getComponent() {
        val component = Component1()
        val component2 = Component2()
        
        assertNotEquals(component, component2)
        
        assertNull(entity.getComponent(Component1::class))
        assertNull(entity.getComponent(Component2::class))
        
        entity.addComponent(component)
        
        assertNotNull(entity.getComponent(Component1::class))
        assertNull(entity.getComponent(Component2::class))
        
        assertEquals(entity.getComponent(Component1::class), component)
        assertNotEquals(entity.getComponent(Component1::class), component2)
        
        entity.addComponent(component2)
        
        assertNotNull(entity.getComponent(Component1::class))
        assertNotNull(entity.getComponent(Component2::class))
        
        assertEquals(entity.getComponent(Component1::class), component)
        assertEquals(entity.getComponent(Component2::class), component2)
        
    }
    
    @Test
    fun removeComponent() {
        val component = Component1()
        val component2 = Component2()
        
        entity.addComponent(component)
        assertTrue(entity.hasComponent(Component1::class))
        assertFalse(entity.hasComponent(Component2::class))
        
        entity.addComponent(component2)
        assertTrue(entity.hasComponent(Component1::class))
        assertTrue(entity.hasComponent(Component2::class))
        
        entity.removeComponent(Component1::class)
        assertFalse(entity.hasComponent(Component1::class))
        assertTrue(entity.hasComponent(Component2::class))
        
        entity.removeComponent(Component2::class)
        assertFalse(entity.hasComponent(Component1::class))
        assertFalse(entity.hasComponent(Component2::class))
    }
    
}