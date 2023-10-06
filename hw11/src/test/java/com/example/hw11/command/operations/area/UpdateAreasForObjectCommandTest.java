package com.example.hw11.command.operations.area;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.hw11.IoCAbstractTest;
import com.example.hw11.game_objects.UObject;
import com.example.hw11.game_objects.UObjectWithId;
import com.example.hw11.game_objects.area.AreaDescriptionObject;
import com.example.hw11.game_objects.area.DescribableByIdAreasObject;
import com.example.hw11.game_objects.area.ObjectsInArea;
import com.example.hw11.game_objects.area.ObjectsInAreaImpl;
import com.example.hw11.game_objects.spaceship.SpaceshipObject;
import com.example.hw11.ioc.IoC;
import com.example.hw11.operations.Areable;
import com.example.hw11.operations.AreableDescription;
import com.example.hw11.operations.DescribableByIdAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

class UpdateAreasForObjectCommandTest extends IoCAbstractTest {
    private UObject object;
    private UObjectWithId describableByIdAreasObject;
    private String areaDescriptionId1 = UUID.randomUUID().toString();
    private String areaDescriptionId2 = UUID.randomUUID().toString();
    private UObject areaDescription1;
    private UObject areaDescription2;
    private AreableDescription areableDescriptionAdapter;

    @BeforeEach
    public void beforeTest() {
        object = new SpaceshipObject();
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
    void shouldSuccessfullyUpdateAreasForObject() {
        // given
        var objId = UUID.randomUUID().toString();
        object.setProperty("Position", new double[]{12, 5});
        object.setProperty("ObjId", objId);
        var expectedArea1 = new int[]{6, 3};
        var expectedArea2 = new int[]{4, 2};

        // when
        new UpdateAreasForObjectCommand(object, describableByIdAreasObject).execute();

        // then
        var areableAdapter = IoC.<Areable>resolve("AreableAdapter", object);
        var describableByIdAreasAdapter = new DescribableByIdAdapter(describableByIdAreasObject);
        var objectsInArea1 = ((ObjectsInArea) describableByIdAreasAdapter
                .getObjectById(areaDescriptionId1)
                .getProperty("ObjectsInArea"))
                .getObjIdsByArea(Arrays.toString(expectedArea1))
                .get();
        var objectsInArea2 = ((ObjectsInArea) describableByIdAreasAdapter
                .getObjectById(areaDescriptionId2)
                .getProperty("ObjectsInArea"))
                .getObjIdsByArea(Arrays.toString(expectedArea2))
                .get();
        var actualAreas = areableAdapter.getAreas().get();
        assertAll(
                () -> assertEquals(objId, areableAdapter.getObjId().get()),
                () -> assertNotNull(actualAreas),
                () -> assertNotNull(actualAreas.get(areaDescriptionId1)),
                () -> assertArrayEquals(expectedArea1, actualAreas.get(areaDescriptionId1)),
                () -> assertNotNull(actualAreas.get(areaDescriptionId2)),
                () -> assertArrayEquals(expectedArea2, actualAreas.get(areaDescriptionId2)),
                () -> assertEquals(1, objectsInArea1.size()),
                () -> assertEquals(objId, objectsInArea1.get(0)),
                () -> assertEquals(1, objectsInArea2.size()),
                () -> assertEquals(objId, objectsInArea2.get(0))
        );
    }
}