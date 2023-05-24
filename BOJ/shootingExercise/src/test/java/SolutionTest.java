

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @DisplayName("")
    @ParameterizedTest
    @MethodSource("provider")
    public void 새로운_표적_생성(List<List<Integer>> target, List<Integer> bullet){
        for(int i=0; i<bullet.size(); i++){

        }

    }

    public static Stream<Arguments> provider(){
        return Stream.of(
                Arguments.arguments(
                List.of(List.of(0,0,0,0,0),
                        List.of(10, 0, 4, 0, 0),
                        List.of(0, 0, 7, 0, 0),
                        List.of(0, 0, 0, 0, 0),
                        List.of(0, 0, 2, 0, 0)),
                        List.of(1, 5, 1)
                ),
                Arguments.arguments(
                        List.of(List.of(0,0,7,0,0),
                                List.of(0,0,5,20,20),
                                List.of(0,6,7,0,0),
                                List.of(0,1,0,0,0),
                                List.of(0,0,2,0,0)),
                        List.of(2, 3, 1, 1, 1)
                ));
    }


}