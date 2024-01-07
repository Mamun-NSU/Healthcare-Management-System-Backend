package com.healthcareapp.communityportalservice;
import com.healthcareapp.communityportalservice.entities.ProgressCheck;
import com.healthcareapp.communityportalservice.exceptions.UnauthorizedException;
import com.healthcareapp.communityportalservice.repositories.ProgressCheckRepository;
import com.healthcareapp.communityportalservice.services.implementations.ProgressCheckServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProgressCheckServiceImplTest {

    @Mock
    private ProgressCheckRepository progressCheckRepository;

    @InjectMocks
    private ProgressCheckServiceImpl progressCheckService;

    @Test
    void testInsertOrUpdateProgressCheck() {
        // Arrange
        String patientId = "testPatientId";
        ProgressCheck progressCheck = new ProgressCheck();
        progressCheck.setCurrentWeight(70.0);

        // Mocking SecurityContextHolder for the current authenticated user
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(patientId);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Act
        assertDoesNotThrow(() -> progressCheckService.insertOrUpdateProgressCheck(patientId, progressCheck));

        // Assert
        ArgumentCaptor<ProgressCheck> progressCheckCaptor = ArgumentCaptor.forClass(ProgressCheck.class);
        verify(progressCheckRepository).save(progressCheckCaptor.capture());

        ProgressCheck capturedProgressCheck = progressCheckCaptor.getValue();
        assertNotNull(capturedProgressCheck);
        assertEquals(patientId, capturedProgressCheck.getPatientId());
        assertEquals(progressCheck.getCurrentWeight(), capturedProgressCheck.getCurrentWeight());
    }

    @Test
    void testInsertOrUpdateProgressCheck_UnauthorizedException() {

        String patientId = "testPatientId";
        ProgressCheck progressCheck = new ProgressCheck();
        progressCheck.setCurrentWeight(70.0);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("anotherPatientId");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertThrows(UnauthorizedException.class, () -> progressCheckService.insertOrUpdateProgressCheck(patientId, progressCheck));
    }





}
