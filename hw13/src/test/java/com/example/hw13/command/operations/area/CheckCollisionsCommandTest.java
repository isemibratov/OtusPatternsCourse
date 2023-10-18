package com.example.hw13.command.operations.area;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

import com.example.hw13.IoCAbstractTest;
import com.example.hw13.command.log.LogErrorCommand;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.game_objects.UObjectWithId;
import com.example.hw13.game_objects.area.AreaDescriptionObject;
import com.example.hw13.game_objects.area.DescribableByIdAreasObject;
import com.example.hw13.game_objects.area.ObjectsInAreaImpl;
import com.example.hw13.game_objects.spaceship.SpaceshipObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.area.AreableDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.UUID;

class CheckCollisionsCommandTest extends IoCAbstractTest {
    private UObject object1;
    private UObject object2;
    private UObjectWithId describableByIdAreasObject;
    private String areaDescriptionId1 = UUID.randomUUID().toString();
    private String areaDescriptionId2 = UUID.randomUUID().toString();
    private UObject areaDescription1;
    private UObject areaDescription2;
    private AreableDescription areableDescriptionAdapter;

    @BeforeEach
    public void beforeTest() {
        object1 = new SpaceshipObject();
        object2 = new SpaceshipObject();
        describableByIdAreasObject = DescribableByIdAreasObject.getInstance();
        areaDescription1 = new AreaDescriptionObject();
        areableDescriptionAdapter = IoC.resolve("AreableDescriptionAdapter", areaDescription1);
        areableDescriptionAdapter.setAreaId(areaDescriptionId1);
        areableDescriptionAdapter.setAxisSize(2);
        areableDescriptionAdapter.setObjectsInArea(new ObjectsInAreaImpl());
        describableByIdAreasObject.setProperty(areaDescriptionId1, areaDescription1);
        areaDescription2 = new AreaDescriptionObject();
        areableDescriptionAdapter = IoC.resolve("AreableDescriptionAdapter", areaDescription2);
        areableDescriptionAdapter.setAreaId(areaDescriptionId2);
        areableDescriptionAdapter.setAxisSize(3);
        areableDescriptionAdapter.setObjectsInArea(new ObjectsInAreaImpl());
        describableByIdAreasObject.setProperty(areaDescriptionId2, areaDescription2);
    }

    @Test
    void shouldSuccessfullyFindCollision() {
        try (MockedConstruction<LogErrorCommand> logErrorCommandConstructionMock = mockConstruction(LogErrorCommand.class)) {
            // given
            var objId1 = UUID.randomUUID().toString();
            var objId2 = UUID.randomUUID().toString();
            object1.setProperty("Position", new double[]{12, 5});
            object1.setProperty("ObjId", objId1);
            object2.setProperty("Position", new double[]{12, 5});
            object2.setProperty("ObjId", objId2);
            new UpdateAreasForObjectCommand(object1, describableByIdAreasObject).execute();
            new UpdateAreasForObjectCommand(object2, describableByIdAreasObject).execute();

            // when
            new CheckCollisionsCommand(object1, describableByIdAreasObject).execute();

            // then
            var logErrorCommandMock = logErrorCommandConstructionMock.constructed().get(0);
            verify(logErrorCommandMock)
                    .execute();
        }
    }
}